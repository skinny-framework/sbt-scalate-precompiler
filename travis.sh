#!/bin/bash
sbt ++2.11.6 \
    precompiler/test \
    precompiler/publishLocal \
    ++2.10.5 \
    precompiler/test \
    precompiler/publishLocal \
    ++2.10.4 clean \
    plugin/publishLocal \
    plugin/scripted
