package org.tyrest.notification.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.notification.face.orm.dao.ArchiveMessageDAO;
import org.tyrest.notification.face.orm.entity.ArchiveMessage;

@Repository(value="archiveMessageDAO")
public class ArchiveMessageDAOImpl extends GenericDAOImpl<ArchiveMessage> implements ArchiveMessageDAO
{
	
}
