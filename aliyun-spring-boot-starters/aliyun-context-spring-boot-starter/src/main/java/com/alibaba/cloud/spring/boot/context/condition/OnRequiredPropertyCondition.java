/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.spring.boot.context.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.PropertySources;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * {@link Conditional} checks the required property is present or not in the
 * {@link PropertySources}.
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
class OnRequiredPropertyCondition extends SpringBootCondition {

    private static final String annotationName = ConditionalOnRequiredProperty.class
            .getName();

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata metadata) {

        Map<String, Object> annotationAttributes = metadata
                .getAnnotationAttributes(annotationName);

        return doGetMatchOutcome(context, annotationAttributes);
    }

    protected static ConditionOutcome doGetMatchOutcome(ConditionContext context,
                                                        Map<String, Object> annotationAttributes) {

        String propertyName = (String) annotationAttributes.get("value");

        return doGetMatchOutcome(context, propertyName);
    }

    protected static ConditionOutcome doGetMatchOutcome(ConditionContext context,
                                                        String propertyName) {
        return context.getEnvironment().containsProperty(propertyName)
                ? ConditionOutcome.match() : ConditionOutcome.noMatch("The property '"
                + propertyName + "' is abstract in the PropertySources");
    }

}
