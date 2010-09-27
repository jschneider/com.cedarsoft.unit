package com.cedarsoft;

import com.cedarsoft.quantity.Derived;
import com.cedarsoft.quantity.Length;
import com.cedarsoft.quantity.SIUnit;
import com.cedarsoft.unit.cm;
import com.cedarsoft.unit.kg;
import com.cedarsoft.unit.m;
import com.cedarsoft.unit.m2;
import com.cedarsoft.unit.m_s;
import com.cedarsoft.unit.s;
import org.junit.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 *
 */
public class UnitTest {
  //  @Test
  //  public void testIt() {
  //    Unit<com.cedarsoft.quantity.Length> metreUnit = MetricSystem.METRE;
  //
  //    {
  //      @m
  //      double metre = 7;
  //      @m
  //      double result = calcMetres( metre );
  //    }
  //
  //    @m
  //    double result = convertToMetres( 37, MetricSystem.CENTI_METRE );
  //  }

  @m
  public static double calcMetres( @m double amount ) {
    return amount;
  }

  @m2
  public static double calcArea( @m double width, @m double height ) {
    return width * height;
  }

  @m_s
  public static double calcSpeed( @m double amount, @s double time ) {
    return amount / time;
  }

  //  @m
  //  public static double convertToMetres( @Length double amount, @NotNull Unit<? extends com.cedarsoft.quantity.Length> unit ) {
  //    if ( unit == MetricSystem.METRE ) {
  //      return amount;
  //    }
  //    if ( unit == MetricSystem.CENTI_METRE ) {
  //      return amount / 100.0;
  //    }
  //
  //    throw new UnsupportedOperationException( "Unsupported unit: " + unit );
  //  }

  @m
  public static double convertToMetres( @cm double centiMetres ) {
    return centiMetres / 100.0;
  }

  @Test
  public void testConvert() throws Exception {
    @cm
    int cm = 100;
    @m
    double result = convertToMetres( cm );
    assertEquals( 1, result, 0 );
  }

  @Test
  public void testSpeed() throws Exception {
    @m_s
    double speed = calcSpeed( 70, 100 );
    assertEquals( 0.7, speed, 0 );
  }

  @Test
  public void testArea() throws Exception {
    @m int width = 7;
    @m int height = 3;
    @m2
    double area = calcArea( width, height );

    assertEquals( 21, area, 0 );
  }

  @Test
  public void testSymbol() throws Exception {
    assertEquals( "m", m.SYMBOL );
    assertEquals( "cm", cm.SYMBOL );
    assertEquals( "kg", kg.SYMBOL );
    assertEquals( "m²", m2.SYMBOL );
    assertEquals( "m/s", m_s.SYMBOL );
    assertEquals( "s", s.SYMBOL );
  }

  @Test
  public void testReflection() throws Exception {
    Method method = UnitTest.class.getMethod( "calcMetres", Double.TYPE );
    assertNotNull( method );

    assertEquals( 1, method.getParameterAnnotations().length );
    assertEquals( 1, method.getParameterAnnotations()[0].length );
    Annotation annotation = method.getParameterAnnotations()[0][0];
    assertEquals( "com.cedarsoft.unit.m", annotation.annotationType().getName() );

    m metre = ( m ) annotation;

    assertTrue( annotation.annotationType().isAnnotationPresent( Length.class ) );
    assertTrue( annotation.annotationType().isAnnotationPresent( SIUnit.class ) );

    SIUnit siUnitAnnotation = annotation.annotationType().getAnnotation( SIUnit.class );
    assertSame( Length.class, siUnitAnnotation.dimension() );
  }

  @Test
  public void testReflCm() throws Exception {
    Method method = UnitTest.class.getMethod( "convertToMetres", Double.TYPE );
    assertNotNull( method );

    assertEquals( 1, method.getParameterAnnotations().length );
    assertEquals( 1, method.getParameterAnnotations()[0].length );

    Annotation annotation = method.getParameterAnnotations()[0][0];
    assertEquals( "com.cedarsoft.unit.cm", annotation.annotationType().getName() );

    assertTrue( annotation.annotationType().isAnnotationPresent( Length.class ) );
    assertTrue( annotation.annotationType().isAnnotationPresent( Derived.class ) );
    assertFalse( annotation.annotationType().isAnnotationPresent( SIUnit.class ) );

    Derived derived = annotation.annotationType().getAnnotation( Derived.class );
    assertEquals( 100, derived.factor(), 0 );
    assertEquals( m.class, derived.from() );
  }
}

