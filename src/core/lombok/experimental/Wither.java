/*
 * Copyright (C) 2012 The Project Lombok Authors.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lombok.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lombok.AccessLevel;

/**
 * Put on any field to make lombok build a 'wither' - a withX method which produces a clone of this object (except for 1 field which gets a new value).
 * <p>
 * Example:
 * <pre>
 *     private &#64;Wither final int foo;
 * </pre>
 * 
 * will generate:
 * 
 * <pre>
 *     public SELF_TYPE withFoo(int foo) {
 *         return this.foo == foo ? this : new SELF_TYPE(otherField1, otherField2, foo);
 *     }
 * </pre>
 * 
 * If any method named {@code withFoo} (case insensitive) exists, regardless of return type or parameters,
 * no method is generated, and instead a compiler warning is emitted.
 * <p>
 * This annotation can also be applied to a class, in which case it'll be as if all non-static fields that don't already have
 * a {@code Wither} annotation have the annotation.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Wither {
	/**
	 * If you want your wither to be non-public, you can specify an alternate access level here.
	 */
	AccessLevel value() default AccessLevel.PUBLIC;
}
