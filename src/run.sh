#!/bin/bash

javac -d ../bin analyser/*.java

java -cp ../bin analyser.Demo

rm -fr ../bin/analyser/*.class
rm -fr ../bin/analyser/fileHandler/*.class
