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

package org.apache.commons.lang3.function;

import java.util.function.Predicate;

/**
 * A functional interface like {@link Predicate} that declares a {@code Throwable}.
 *
 * @param <I> Predicate type 1.
 * @param <T> Thrown exception.
 * @since 3.11
 */
@FunctionalInterface
public interface FailablePredicate<I, T extends Throwable> {

    /**
     * Tests the predicate.
     *
     * @param object the object to test the predicate on
     * @return the predicate's evaluation
     * @throws T if the predicate fails
     */
    boolean test(I object) throws T;
}
