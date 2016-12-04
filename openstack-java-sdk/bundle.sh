#!/bin/sh
#*******************************************************************************
# Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
#*******************************************************************************
mvn source:jar javadoc:jar package gpg:sign repository:bundle-create -Dgpg.passphrase=$1