#!/bin/bash

echo "instalando bamboo commons"
grails clean && grails refresh-dependencies && grails maven-install

