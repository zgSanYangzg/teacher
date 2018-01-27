package org.tyrest.core.rest.doc.swagger.core;

import java.util.List;

import org.tyrest.core.rest.doc.swagger.readers.Command;

//T - result type, typically Map<String, Object>
public class CommandExecutor<T, C> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T execute(List<? extends Command<C>> commands, CommandContext<T> context) {
		if (null != commands) {
			for (Command command : commands) {
				command.execute(context);
			}
			return context.getResult();
		}
		return null;
	}
}
