/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Arrays;

import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

/**
 * Tests that {@link EqualsBuilder} works using reflection when types that implement JRE interfaces like TemporalAccessor, TemporalAmout, and CharSequence work.
 */
public class EqualsBuilderReflectJreImplementationTest extends AbstractLangTest {

    static class MyCharSequence implements CharSequence {

        private final char[] chars;

        MyCharSequence(final char[] chars) {
            this.chars = Arrays.copyOf(chars, chars.length);
        }

        MyCharSequence(final char[] chars, final int start, final int end) {
            this.chars = Arrays.copyOfRange(chars, start, end);
        }

        MyCharSequence(final String string) {
            this.chars = string.toCharArray();
        }

        @Override
        public char charAt(final int index) {
            return chars[index];
        }

        @Override
        public int length() {
            return chars.length;
        }

        @Override
        public CharSequence subSequence(final int start, final int end) {
            return new MyCharSequence(chars, start, end);
        }

        @Override
        public String toString() {
            return new String(chars);
        }
    }

    static class MyClass {

        private final MyTemporalAccessor time;
        private final MyCharSequence string;

        MyClass(final MyTemporalAccessor time, final MyCharSequence string) {
            this.time = time;
            this.string = string;
        }

        @Override
        public String toString() {
            return String.format("%s[%s - %s]", getClass().getSimpleName(), string, time);
        }
    }

    static class MyTemporalAccessor implements TemporalAccessor {

        private final String string;
        private final long value;
        private final Instant instant;
        private final Duration duration;

        MyTemporalAccessor(final String string) {
            this.string = string;
            this.value = Long.parseLong(string);
            this.instant = Instant.ofEpochMilli(value);
            this.duration = Duration.between(instant, instant.plusMillis(value));
        }

        @Override
        public long getLong(final TemporalField field) {
            return 0;
        }

        @Override
        public boolean isSupported(final TemporalField field) {
            return false;
        }

        @Override
        public String toString() {
            return String.format("%s[%s - %s - %s]", getClass().getSimpleName(), string, instant, duration);
        }

    }

    @Test
    public void testRecursive() {
        final MyClass o1 = new MyClass(new MyTemporalAccessor("1"), new MyCharSequence("2"));
        // This gives you different instances of MyTemporalAccessor for 1 (and 2) that should be equals by reflection.
        final MyClass o1Bis = new MyClass(new MyTemporalAccessor("1"), new MyCharSequence("2"));
        final MyClass o2 = new MyClass(new MyTemporalAccessor("3"), new MyCharSequence("4"));
        final MyClass o2Bis = new MyClass(new MyTemporalAccessor("3"), new MyCharSequence("4"));
        // MyTemporalAccessor
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(new MyTemporalAccessor("1"), new MyTemporalAccessor("1")).isEquals());
        // MyCharSequence
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(new MyCharSequence("1"), new MyCharSequence("1")).isEquals());
        // My Class
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1, o1).isEquals(), o1::toString);
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1, o1Bis).isEquals(), o1::toString);
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o2, o2).isEquals(), o2::toString);
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o2, o2Bis).isEquals(), o2::toString);
        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1, o2).isEquals());
        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o2, o1).isEquals());
    }

}
