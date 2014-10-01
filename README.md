clouddeck
====
[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/chidakiyo/clouddeck?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


### For Developper
----

##### Required modules

- VMware-vSphere-CLI-5.1.0-780721.exe (on Windows)
- VMware-vSphere-CLI-5.1.0-780721.x86_64.tar.gz (on Linux 64bit)
- VMware-vSphere-CLI-5.5.0-1384587.i386.tar.gz (on Linux 32bit)

 Use appropriate to the execution environment

##### Follow these steps to get started:

1. Git-clone this repository.

        $ git clone git://github.com/chidakiyo/clouddeck.git clouddeck

1. Change directory into your clone:

        $ cd clouddeck

1. Launch SBT:

        $ sbt

1. Compile everything and run all tests:

        > test

1. Start the application:

        > re-start

1. Browse to [http://localhost:8080](http://localhost:8080/)

1. Stop the application:

        > re-stop

##### Grunt environment installation (on Windows)

1. Install Chocolatey

        @powershell -NoProfile -ExecutionPolicy unrestricted -Command "iex ((new-object net.webclient).DownloadString('https://chocolatey.org/install.ps1'))" && SET PATH=%PATH%;%systemdrive%\chocolatey\bin

1. Install node.js

        > cinst nodejs.install

1. Install yo,grunt,bower

        > npm install -g yo
        > npm install -g grunt-cli bower
        > npm install -g grunt
        > npm install -g bower

1. Install Ruby

        > cinst ruby

1. Install compass gem

        > gem install compass

##### Fowwow these steps to view development

1. Change directory into webapps

        > cd clouddeck/src/main/webapp

1. Install modules (first time only)

        > bower install
        > npm install

1. Launch Grunt

        > Grunt serve

1. Start your browser automatically  


##### Build and clean

1. build

        > grunt build

1. Clean

        > grunt clean