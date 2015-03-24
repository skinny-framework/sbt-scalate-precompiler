#!/bin/bash
sbt ++2.11.6 \
    clean \
    precompiler/publishSigned \
    ++2.10.5 \
    clean \
    precompiler/publishSigned \
    ++2.10.4 \
    clean \
    plugin/publishSigned
