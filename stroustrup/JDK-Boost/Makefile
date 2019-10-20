all:
	g++ boost.cpp -o boost -lboost_system -lboost_filesystem
	mkdir jdk8
	mkdir jdk12
	unzip src.zip -d jdk8/
	unzip src12.zip -d jdk12/
clean:
	rm boost -f
	rm -rf jdk8 jdk12
