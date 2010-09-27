package com.cedarsoft.unit;

import com.cedarsoft.quantity.SIBaseUnit;
import com.cedarsoft.quantity.Time;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Represents a metre
 *
 * @noinspection AnnotationNamingConvention
 */
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Inherited

@Time
@SIBaseUnit( Time.class )
public @interface s {
  @NotNull
  @NonNls
  String SYMBOL = "s";
}
