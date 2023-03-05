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
package io.github.misat11.owojava.test;

import org.junit.jupiter.api.Test;

import javax.tools.ToolProvider;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OwoPluginTest {
    @Test
    public void testCompilation() {
        assertDoesNotThrow(() -> {
            var compiler = ToolProvider.getSystemJavaCompiler();
            var options = List.of("-d", new File("example/out").getAbsolutePath(), "-Xplugin:OwoPlugin");
            var manager = compiler.getStandardFileManager(null, null, null);
            var objects = manager.getJavaFileObjects(
                    new File("example/src/Example.java").getAbsoluteFile()
            );
            var task = compiler.getTask(null, null, null, options, null, objects);

            assertTrue(task.call());
        });
    }
}
