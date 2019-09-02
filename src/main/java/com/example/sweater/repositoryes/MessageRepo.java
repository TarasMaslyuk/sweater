/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2019 VTB Group. All rights reserved.
 */

package com.example.sweater.repositoryes;

import com.example.sweater.domain.Message;
import org.aspectj.bridge.IMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MessageRepo.
 *
 * @author Taras
 */
@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}