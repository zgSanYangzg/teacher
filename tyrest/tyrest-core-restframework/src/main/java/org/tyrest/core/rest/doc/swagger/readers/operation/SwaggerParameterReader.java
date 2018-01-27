package org.tyrest.core.rest.doc.swagger.readers.operation;

import com.mangofactory.swagger.models.dto.Parameter;

import java.util.Collection;
import java.util.List;

import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

import static com.google.common.collect.Lists.*;

public abstract class SwaggerParameterReader implements RequestMappingReader {

	@SuppressWarnings("unchecked")
	@Override
	public final void execute(RequestMappingContext context) {
		List<Parameter> parameters = (List<Parameter>) context.get("parameters");
		if (parameters == null) {
			parameters = newArrayList();
		}
		parameters.addAll(this.readParameters(context));
		context.put("parameters", parameters);
	}

	abstract protected Collection<? extends Parameter> readParameters(RequestMappingContext context);
}
