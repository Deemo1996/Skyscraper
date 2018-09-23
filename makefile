JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Skyscraper.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.clas
