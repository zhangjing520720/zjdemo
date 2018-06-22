package com.sdy.util.elasticsearch;
import java.lang.annotation.*;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface Field {

	FieldType type() default FieldType.Auto;

	FieldIndex index() default FieldIndex.analyzed;

	DateFormat format() default DateFormat.none;

	String pattern() default "";

	boolean store() default false;

	String searchAnalyzer() default "";

	String indexAnalyzer() default "";

	String[] ignoreFields() default {};

	boolean includeInParent() default false;
}