/*
 * This file is part of OwO Java, licensed under the MIT License.
 *
 * Copyright (c) 2023 Misat11
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.misat11.owojava;

import com.sun.source.util.*;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.util.Names;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Map;

public class OwoPlugin implements Plugin {
    private static Unsafe unsafe;

    static {
        try {
            final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "OwoPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        try {
            var context = ((BasicJavacTask) task).getContext();

            // Old Java syntax features

            var tokens = Tokens.instance(context);
            var field = Tokens.TokenKind.class.getDeclaredField("name");
            var nameMapField = Tokens.class.getDeclaredField("keywords");
            @SuppressWarnings("unchecked")
            var nameMap = (Map<String, Tokens.TokenKind>) unsafe.getObject(tokens, unsafe.objectFieldOffset(nameMapField));

            var fieldOffset = unsafe.objectFieldOffset(field);

            for (var token : Tokens.TokenKind.values()) {
                String name;
                switch (token) {
                    case ABSTRACT:
                        name = "abstwact";
                        break;
                    case BOOLEAN:
                        name = "boowean";
                        break;
                    case BREAK:
                        name = "bweak";
                        break;
                    case CHAR:
                        name = "chaw";
                        break;
                    case CLASS:
                        name = "cwass";
                        break;
                    case CONTINUE:
                        name = "continyue";
                        break;
                    case DEFAULT:
                        name = "defauwt";
                        break;
                    case DOUBLE:
                        name = "doubwe";
                        break;
                    case ELSE:
                        name = "ewse";
                        break;
                    case ENUM:
                        name = "enyum";
                        break;
                    case FINAL:
                        name = "finyaw";
                        break;
                    case FINALLY:
                        name = "finyawwy";
                        break;
                    case FLOAT:
                        name = "fwoat";
                        break;
                    case FOR:
                        name = "fow";
                        break;
                    case IMPLEMENTS:
                        name = "impwements";
                        break;
                    case IMPORT:
                        name = "impowt";
                        break;
                    case INTERFACE:
                        name = "intewface";
                        break;
                    case LONG:
                        name = "wong";
                        break;
                    case NATIVE:
                        name = "nyative";
                        break;
                    case NEW:
                        name = "nyew";
                        break;
                    case PRIVATE:
                        name = "pwivate";
                        break;
                    case PROTECTED:
                        name = "pwotected";
                        break;
                    case PUBLIC:
                        name = "pubwic";
                        break;
                    case RETURN:
                        name = "wetuwn";
                        break;
                    case SHORT:
                        name = "showt";
                        break;
                    case STRICTFP:
                        name = "stwictfp";
                        break;
                    case SUPER:
                        name = "supew";
                        break;
                    case SYNCHRONIZED:
                        name = "synchwonyized";
                        break;
                    case THROW:
                        name = "thwow";
                        break;
                    case THROWS:
                        name = "thwows";
                        break;
                    case TRANSIENT:
                        name = "twansient";
                        break;
                    case TRY:
                        name = "twy";
                        break;
                    case VOLATILE:
                        name = "vowatiwe";
                        break;
                    case WHILE:
                        name = "whiwe";
                        break;
                    case TRUE:
                        name = "twue";
                        break;
                    case FALSE:
                        name = "fawse";
                        break;
                    case NULL:
                        name = "nyuww";
                        break;
                    default:
                        name = null;
                        break;
                }

                if (name != null) {
                    var oldName = token.name;
                    nameMap.remove(oldName);
                    nameMap.put(name, token);
                    unsafe.putObject(token, fieldOffset, name);
                }
            }

            // New language features (and few duplicates as well??)
            var names = Names.instance(context);

            patchNew(names, "_class", "cwass");
            patchNew(names, "_super", "supew");
            patchNew(names, "var", "vaw");
            patchNew(names, "exports", "expowts");
            patchNew(names, "module", "moduwe");
            patchNew(names, "provides", "pwovides");
            patchNew(names, "requires", "wequiwes");
            patchNew(names, "transitive", "twansitive");
            patchNew(names, "yield", "yiewd");

            // Despite this patch is successful, it will not alter the error message.
            // It is hardcoded for some reason, and I don't want to manipulate with constant pool of loaded class.
            patchNew(names, "record", "wecowd");

            patchNew(names, "sealed", "seawed");
            patchNew(names, "permits", "pewmits");
            patchNew(names, "non", "nyon");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private void patchNew(Names names, String fieldName, String newName) {
        try {
            var field = Names.class.getDeclaredField(fieldName);
            unsafe.putObject(names, unsafe.objectFieldOffset(field), names.fromString(newName));
        } catch (NoSuchFieldException ignored) {
            // not present in this version of JDK
        }
    }
}
