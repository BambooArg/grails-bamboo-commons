package ar.com.bamboo.commons.file

import groovy.transform.CompileStatic

/**
 * Created by orko on 25/09/14.
 */
@CompileStatic
class BambooFile {

    Long id
    String filename
    byte[] filedata
    String contentType
    int size
}
