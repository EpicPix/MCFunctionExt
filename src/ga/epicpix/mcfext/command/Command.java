package ga.epicpix.mcfext.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.advancements.Advancement;
import ga.epicpix.mcfext.datapacks.Datapack;
import ga.epicpix.mcfext.datapacks.DeclaredFunction;
import ga.epicpix.mcfext.datapacks.Namespace;
import ga.epicpix.mcfext.exceptions.SyntaxNotHandledException;
import ga.epicpix.mcfext.pos.Vec2d;
import ga.epicpix.mcfext.pos.Vec3d;

import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

import static ga.epicpix.mcfext.Utils.*;

public final class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/commands.json")), new TypeToken<ArrayList<Command>>(){}.getType()));
    }

    private Object name;
    private Object syntax;
    private String warning;

    private Object parseObjs(Datapack pack, DeclaredFunction fun, Object syntax, CommandStringIterator data, Variables vars, ArrayList<Object> vals) {
        debug("parseObjs(" + getName() + ") : " + syntax);
        if(syntax instanceof String) {
            String syn = (String) syntax;
            String[] sargs = syn.split(" ");
            String sarg0 = sargs[0];
            String sarg1 = sargs.length > 1 ? sargs[1] : null;
            if(sarg0.equals("@any")) {
                vals.add(data.restW());
                return vals;
            }else if(sarg0.equals("@command")) {
                int back = data.getPosition();
                Command cmd;
                if(sarg1 != null) {
                    cmd = getCommand(sarg1);
                }else {
                    String cmdname = data.nextWord();
                    vals.add(cmdname);
                    cmd = getCommand(cmdname);
                }
                if(cmd == null) {
                    data.setPosition(back);
                    return new CommandError("Unknown command");
                }
                Object parsed = cmd.parse(pack, fun, data, vars);
                if(parsed == null || parsed instanceof CommandError) {
                    return parsed;
                }
                vals.addAll(Arrays.asList(((CommandData) parsed).getData()));
                return vals;
            }else if(sarg0.equals("@end")) {
                return vals;
            }else if(sarg0.equals("@error")) {
                if(sarg1 != null) {
                    return new CommandError(syn.split(" ", 2)[1]);
                }else {
                    return new CommandError("Undefined error");
                }
            }
            throw new SyntaxNotHandledException("Not handled syntax: " + syn);
        }else if(syntax instanceof Map) {
            data.removeNextWhitespace();
            Map<String, Object> sMap = (Map<String, Object>) syntax;
            for(Entry<String, Object> entry : sMap.entrySet()) {
                String[] values = entry.getKey().split("\\|");
                for(String val : values) {
                    int pos = data.getPosition();
                    if(val.startsWith("@")) {
                        String[] args = val.split(" ");
                        if(args[0].equals("@advancement")) {
                            ResourceLocation radv = data.nextResourceLocation();
                            Advancement adv = Advancement.getAdvancement(radv);
                            if(adv != null) {
                                vals.add(adv);
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }
                        }else if(args[0].equals("@boolean")) {
                            String w = data.nextWord();
                            if(w.equals("false") || w.equals("true")) {
                                vals.add(w.equals("true"));
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }
                        }else if(args[0].equals("@criterion")) {
                            Advancement adv = null;
                            for(Object obj : vals) {
                                if(obj instanceof Advancement) {
                                    adv = (Advancement) obj;
                                }
                            }
                            if(adv == null) {
                                return new CommandError("Cannot get advancement");
                            }else {
                                String v = data.nextWord();
                                Object crt = adv.getCriterion(v);
                                if(crt != null) {
                                    vals.add(crt);
                                    return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                                }else {
                                    return new CommandError("Advancement doesn't contain that criterion");
                                }
                            }
                        }else if(args[0].equals("@double")) {
                            try {
                                vals.add(Double.parseDouble(data.nextWord()));
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }catch(NumberFormatException ignored) {}
                        }else if(args[0].equals("@else")) {
                            return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                        }else if(args[0].equals("@function")) {
                            ResourceLocation loc = data.nextResourceLocation(fun.getResourceLocation().getNamespace());
                            Namespace ns = pack.getNamespace(loc.getNamespace());
                            if(ns != null) {
                                DeclaredFunction f = ns.getDeclaredFunction(loc.getLocation());
                                if(f != null) {
                                    vals.add(f);
                                    return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                                }else {
                                    return new CommandError("Cannot find function");
                                }
                            }else {
                                return new CommandError("Cannot find namespace");
                            }
                        }else if(args[0].equals("@has")) {
                            if(data.hasNext()) {
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }
                        }else if(args[0].equals("@int")) {
                            try {
                                vals.add(Integer.parseInt(data.nextWord()));
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }catch(NumberFormatException ignored) {}
                        }else if(args[0].equals("@none")) {
                            if(!data.hasNext()) {
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }
                        }else if(args[0].equals("@resource")) {
                            vals.add(data.nextResourceLocation());
                            return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                        }else if(args[0].equals("@selector")) {
                            vals.add(data.nextSelector());
                            return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                        }else if(args[0].equals("@uuid")) {
                            try {
                                vals.add(UUID.fromString(data.nextWord()));
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            } catch(IllegalArgumentException ignored) {}
                        }else if(args[0].equals("@vec2d")) {
                            Vec2d vec2d = data.nextVec2d();
                            if(vec2d != null) {
                                vals.add(vec2d);
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }
                        }else if(args[0].equals("@vec3d")) {
                            Vec3d vec3d = data.nextVec3d();
                            if(vec3d != null) {
                                vals.add(vec3d);
                                return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                            }
                        }else if(args[0].equals("@word")) {
                            vals.add(data.nextWord());
                            return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                        }else {
                            throw new SyntaxNotHandledException("Not handled syntax selector: " + val);
                        }
                    }else {
                        String v = data.nextWord();
                        if(val.equals(v)) {
                            vals.add(v);
                            return parseObjs(pack, fun, entry.getValue(), data, vars, vals);
                        }
                    }
                    data.setPosition(pos);
                }
            }
            return new CommandError("Could not parse '" + getName() + "' command");
        }else {
            throw new SyntaxNotHandledException("Not handled syntax, unknown syntax type " + syntax.getClass() + "  /  " + syntax);
        }
    }

    public Object parse(Datapack pack, DeclaredFunction fun, CommandStringIterator data, Variables vars) {
        if(warning!=null) {
            warn("[" + name + "] " + warning);
        }
        Object objs = parseObjs(pack, fun, syntax, data, vars, new ArrayList<>());

        if(objs==null) return null;
        if(objs instanceof CommandError) return objs;

        if(objs instanceof ArrayList) {
            return new CommandData(this, ((ArrayList<Object>) objs).toArray(new Object[0]));
        }else if(objs instanceof Object[]) {
            return new CommandData(this, (Object[]) objs);
        }else {
            return new CommandData(this, new Object[] {objs});
        }
    }

    public String getName() {
        if(name instanceof String) {
            return name.toString();
        }else if(name instanceof ArrayList) {
            return ((ArrayList<String>) name).get(0);
        }else {
            return null;
        }
    }

    public String toString() {
        return getName();
    }

    public static Command getCommand(String name) {
        for(Command cmd : COMMANDS) {
            if(cmd.name instanceof String) {
                if (cmd.name.equals(name)) {
                    return cmd;
                }
            }else if(cmd.name instanceof ArrayList) {
                for(String alias : (ArrayList<String>) cmd.name) {
                    if(alias.equals(name)) {
                        return cmd;
                    }
                }
            }
        }
        return null;
    }

}
