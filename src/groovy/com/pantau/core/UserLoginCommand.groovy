package com.pantau.core

/**
 * Created by widodo on 22/08/15.
 */
import grails.validation.Validateable
import org.apache.commons.lang.RandomStringUtils;

@Validateable
class UserLoginCommand {
    int randomStringLength = 7
    String charset = (('0'..'9')).join()
    def randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())

    String username
    String password
    String email
    String ktp = "998089"
    String nohp = randomString
    String alamat= "989990"
    String kodepos= "75869"
    String nama
    static constraints = {
        email nullable: true, blank: true
        nama nullable:true, blank:true
        ktp nullable: true, blank: true
        alamat nullable:true, blank:true
        kodepos nullable: true,blank: true
    }
}
