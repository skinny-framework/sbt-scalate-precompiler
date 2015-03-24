#!/bin/bash
sbt clean \
    +precompiler/test \
    +precompiler/publishLocal \
    ++2.10.4 clean \
    plugin/publishLocal \
    plugin/scripted
