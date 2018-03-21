package nz.net.osnz.app.domain

import nz.net.osnz.common.ebean.entity.TimestampModel

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Entity
@Table(name = 'o_user')
class User extends TimestampModel {

    @Column(name = 'username')
    String username

    @Column(name = 'password')
    String password

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = 'user', orphanRemoval = true)
    Set<UserRole> roles

}
