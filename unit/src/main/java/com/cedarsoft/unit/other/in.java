/**
 * Copyright (C) cedarsoft GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cedarsoft.unit.other;

import com.cedarsoft.unit.Unit;
import com.cedarsoft.unit.quantity.Length;
import com.cedarsoft.unit.AlternativeSymbols;
import com.cedarsoft.unit.Definition;
import com.cedarsoft.unit.DerivedUnit;
import com.cedarsoft.unit.Name;
import com.cedarsoft.unit.Symbol;
import com.cedarsoft.unit.quantity.Length;
import com.cedarsoft.unit.si.m;

import javax.annotation.Nonnull;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Inch
 *
 * @author Johannes Schneider (<a href="mailto:js@cedarsoft.com">js@cedarsoft.com</a>)
 */
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Inherited

@Length
@Name( "inch" )
@Symbol( in.SYMBOL )
@AlternativeSymbols( {in.ALTERNATIVE_SYMBOL, in.SYMBOL_SAFE} )
@Unit
@DerivedUnit( m.class )
@Definition( {"0.0254 m", "12 inches = 1 foot"} )
public @interface in {
  /**
   * 1 ft = FEET_RATIO in
   */
  int FEET_RATIO = 12;

  /**
   * Conversion to cm: n * MM_RATIO
   */
  double MM_RATIO = 25.4;

  @Nonnull
  String SYMBOL = "″";
  /**
   * This is a "safe" symbol - that is not correct!
   */
  @Nonnull
  String SYMBOL_SAFE = "\"";
  @Nonnull
  String ALTERNATIVE_SYMBOL = "in";
}
