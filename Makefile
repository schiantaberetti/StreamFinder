all: compile
	jar cmf MANIFEST.MF StreamFinder.jar lib org -C bin . 

bin_dir:
	if [ ! -d bin ];then mkdir bin; fi
	
compile: compile_model compile_engine compile_view 
	
	
compile_model: bin_dir
	javac -d bin src/model/Link.java src/model/LinkCollector.java

compile_engine: bin_dir compile_model
	javac -d bin -classpath ".:lib/*.jar" src/core/SearchEngine.java src/core/Slave.java src/core/XMLStuff.java

compile_view: bin_dir compile_engine
	javac -d bin -classpath lib,bin,org src/view/Gui.java src/view/GuiUrlNotifier.java

clean:
	rm -R bin
	rm $JAR_FILE
