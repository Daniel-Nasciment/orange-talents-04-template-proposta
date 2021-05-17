package com.orange.proposta.orange.validation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD })
@Constraint(validatedBy = {})
@CPF
@ConstraintComposition(CompositionType.OR)
@CNPJ
@Retention(RetentionPolicy.RUNTIME)
public @interface CPForCNPJ {
	String message() default "Documento inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}