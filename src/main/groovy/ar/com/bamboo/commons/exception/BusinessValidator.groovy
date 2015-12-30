package ar.com.bamboo.commons.exception

import groovy.transform.CompileStatic

/**
 * Created by orko on 16/12/14.
 */
@CompileStatic
class BusinessValidator extends RuntimeException{

    public BusinessValidator(String message){
        super(message)
    }
}
