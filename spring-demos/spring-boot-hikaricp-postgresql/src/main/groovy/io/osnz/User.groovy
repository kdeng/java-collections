package io.osnz

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@CompileStatic
@Entity(name="table_user")
class User {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = 'id', columnDefinition = "serial")
  Long id

  @Column
  String name

  @Column
  String email

}
