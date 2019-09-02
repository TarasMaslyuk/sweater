/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2019 VTB Group. All rights reserved.
 */

package com.example.sweater.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Message.
 *
 * @author Taras
 */
@Entity
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;


    @Getter
    @Setter
    @Column(name = "text")
    private String text;
    @Getter
    @Setter
    @Column(name = "tag")
    private String tag;

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
}