#!/bin/bash
sbt clean \
    +precompiler/publishSigned \
    ++2.10.4 clean \
    plugin/publishSigned
