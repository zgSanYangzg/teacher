package org.tyrest.core.rest.doc.swagger.readers;

public interface Command<T> {
  public void execute(T context);
}
