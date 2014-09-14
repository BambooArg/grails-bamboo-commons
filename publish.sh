#!/bin/bash

echo "instalando bamboo commons"
grails clean && grails refresh-dependencies && grails publish-plugin

#grails publish-plugin 

