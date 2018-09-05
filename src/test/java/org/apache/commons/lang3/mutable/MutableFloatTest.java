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
package org.apache.commons.lang3.mutable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit tests.
 *
 * @see MutableFloat
 */
public class MutableFloatTest {

    // ----------------------------------------------------------------
    @Test
    public void testConstructors() {
        assertEquals(new MutableFloat().floatValue(), 0.0001f, 0f);
        assertEquals(new MutableFloat(1f).floatValue(), 0.0001f, 1f);
        assertEquals(new MutableFloat(Float.valueOf(2f)).floatValue(), 0.0001f, 2f);
        assertEquals(new MutableFloat(new MutableFloat(3f)).floatValue(), 0.0001f, 3f);
        assertEquals(new MutableFloat("2.0").floatValue(), 0.0001f, 2f);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new MutableFloat((Number) null);
    }

    @Test
    public void testGetSet() {
        final MutableFloat mutNum = new MutableFloat(0f);
        assertEquals(new MutableFloat().floatValue(), 0.0001f, 0f);
        assertEquals(Float.valueOf(0), new MutableFloat().getValue());
        mutNum.setValue(1);
        assertEquals(mutNum.floatValue(), 0.0001f, 1f);
        assertEquals(Float.valueOf(1f), mutNum.getValue());
        mutNum.setValue(Float.valueOf(2f));
        assertEquals(mutNum.floatValue(), 0.0001f, 2f);
        assertEquals(Float.valueOf(2f), mutNum.getValue());
        mutNum.setValue(new MutableFloat(3f));
        assertEquals(mutNum.floatValue(), 0.0001f, 3f);
        assertEquals(Float.valueOf(3f), mutNum.getValue());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNull() {
        final MutableFloat mutNum = new MutableFloat(0f);
        mutNum.setValue(null);
    }

    @Test
    public void testNanInfinite() {
        MutableFloat mutNum = new MutableFloat(Float.NaN);
        assertTrue(mutNum.isNaN());
        mutNum = new MutableFloat(Float.POSITIVE_INFINITY);
        assertTrue(mutNum.isInfinite());
        mutNum = new MutableFloat(Float.NEGATIVE_INFINITY);
        assertTrue(mutNum.isInfinite());
    }

    @Test
    public void testEquals() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);
        assertTrue(mutNumA.equals(mutNumA));
        assertTrue(mutNumA.equals(mutNumB));
        assertTrue(mutNumB.equals(mutNumA));
        assertTrue(mutNumB.equals(mutNumB));
        assertFalse(mutNumA.equals(mutNumC));
        assertFalse(mutNumB.equals(mutNumC));
        assertTrue(mutNumC.equals(mutNumC));
        assertFalse(mutNumA.equals(null));
        assertFalse(mutNumA.equals(Float.valueOf(0f)));
        assertFalse(mutNumA.equals("0"));
    }

    @Test
    public void testHashCode() {
        final MutableFloat mutNumA = new MutableFloat(0f);
        final MutableFloat mutNumB = new MutableFloat(0f);
        final MutableFloat mutNumC = new MutableFloat(1f);
        assertTrue(mutNumA.hashCode() == mutNumA.hashCode());
        assertTrue(mutNumA.hashCode() == mutNumB.hashCode());
        assertFalse(mutNumA.hashCode() == mutNumC.hashCode());
        assertTrue(mutNumA.hashCode() == Float.valueOf(0f).hashCode());
    }

    @Test
    public void testCompareTo() {
        final MutableFloat mutNum = new MutableFloat(0f);
        assertEquals(0, mutNum.compareTo(new MutableFloat(0f)));
        assertEquals(+1, mutNum.compareTo(new MutableFloat(-1f)));
        assertEquals(-1, mutNum.compareTo(new MutableFloat(1f)));
    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {
        final MutableFloat mutNum = new MutableFloat(0f);
        mutNum.compareTo(null);
    }

    @Test
    public void testPrimitiveValues() {
        final MutableFloat mutNum = new MutableFloat(1.7F);
        assertEquals(1, mutNum.intValue());
        assertEquals(mutNum.doubleValue(), 0.00001, 1.7);
        assertEquals((byte) 1, mutNum.byteValue());
        assertEquals((short) 1, mutNum.shortValue());
        assertEquals(1, mutNum.intValue());
        assertEquals(1L, mutNum.longValue());
    }

    @Test
    public void testToFloat() {
        assertEquals(Float.valueOf(0f), new MutableFloat(0f).toFloat());
        assertEquals(Float.valueOf(12.3f), new MutableFloat(12.3f).toFloat());
    }

    @Test
    public void testIncrement() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.increment();
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testIncrementAndGet() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.incrementAndGet();
        assertEquals(result, 0.01f, 2f);
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testGetAndIncrement() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndIncrement();
        assertEquals(result, 0.01f, 1f);
        assertEquals(2, mutNum.intValue());
        assertEquals(2L, mutNum.longValue());
    }

    @Test
    public void testDecrement() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.decrement();
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testDecrementAndGet() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.decrementAndGet();
        assertEquals(result, 0.01f, 0f);
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testGetAndDecrement() {
        final MutableFloat mutNum = new MutableFloat(1f);
        final float result = mutNum.getAndDecrement();
        assertEquals(result, 0.01f, 1f);
        assertEquals(0, mutNum.intValue());
        assertEquals(0L, mutNum.longValue());
    }

    @Test
    public void testAddValuePrimitive() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.add(1.1f);
        assertEquals(mutNum.floatValue(), 0.01f, 2.1f);
    }

    @Test
    public void testAddValueObject() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.add(Float.valueOf(1.1f));
        assertEquals(mutNum.floatValue(), 0.01f, 2.1f);
    }

    @Test
    public void testGetAndAddValuePrimitive() {
        final MutableFloat mutableFloat = new MutableFloat(1.25f);
        final float result = mutableFloat.getAndAdd(0.75f);
        assertEquals(result, 0.01f, 1.25f);
        assertEquals(mutableFloat.floatValue(), 0.01f, 2f);
    }

    @Test
    public void testGetAndAddValueObject() {
        final MutableFloat mutableFloat = new MutableFloat(7.75f);
        final float result = mutableFloat.getAndAdd(Float.valueOf(2.25f));
        assertEquals(result, 0.01f, 7.75f);
        assertEquals(mutableFloat.floatValue(), 0.01f, 10f);
    }

    @Test
    public void testAddAndGetValuePrimitive() {
        final MutableFloat mutableFloat = new MutableFloat(0.5f);
        final float result = mutableFloat.addAndGet(1f);
        assertEquals(result, 0.01f, 1.5f);
        assertEquals(mutableFloat.floatValue(), 0.01f, 1.5f);
    }

    @Test
    public void testAddAndGetValueObject() {
        final MutableFloat mutableFloat = new MutableFloat(5f);
        final float result = mutableFloat.addAndGet(Float.valueOf(2.5f));
        assertEquals(result, 0.01f, 7.5f);
        assertEquals(mutableFloat.floatValue(), 0.01f, 7.5f);
    }

    @Test
    public void testSubtractValuePrimitive() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.subtract(0.9f);
        assertEquals(mutNum.floatValue(), 0.01f, 0.1f);
    }

    @Test
    public void testSubtractValueObject() {
        final MutableFloat mutNum = new MutableFloat(1);
        mutNum.subtract(Float.valueOf(0.9f));
        assertEquals(mutNum.floatValue(), 0.01f, 0.1f);
    }

    @Test
    public void testToString() {
        assertEquals("0.0", new MutableFloat(0f).toString());
        assertEquals("10.0", new MutableFloat(10f).toString());
        assertEquals("-123.0", new MutableFloat(-123f).toString());
    }
}
