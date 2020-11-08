#Docker
Docker is a tool which is used to automate the deployment of applications in lightweight containers so that applications can work efficiently in different environments.
Just as Java gave us WORA (Write Once Run Anywhere), similarly Docker gives us PODA (Package Once Deploy Anywhere).

###Docker Commands   

#####docker run 
    docker run hello-world 
1. This command asks docker to create and run a container based on the hello-world image.
2. If the image is not present on the disk/system, docker download it from a default registry, the docker hub
3. docker created a container based on the hello-world image
4. It runs the image.
#####docker --help
     docker run --help
This will list all the command that can are available to be used
#####docker ps 
lists the containers that are still running. Add the -a switch in order to see containers that have stopped
#####docker logs
retrieves the logs of a container, even when it has stopped
    
    --from 
    --until 
    --tail
#####docker inspect
gets detailed information about a running or stopped container
#####docker stop
stops a running container
#####docker rm   
deletes a container
#####docker container prune -f
this is the equivalent of running one docker rm command for each stopped container
#####docker -d
    docker run -d alphine
    docker run -d alpine ping www.docker.com
-d this is used for running the docker in background, that is detach mode. This is used for running the container in detach mode,   
we don't get the output of the container rather just the id of the container
#####docker -p
    docker run -d -p 8085:80 nginx 
The -p switch takes two parameters; the incoming port you want to open on the host machine, and the port to which it should be mapped inside the container.
#####list docker images
    docker images ls
It will list all the local images.  
Docker first looks for the image locally and uses it when present. When the image is not present locally, it is downloaded from a registry
#####docker pull
A Pull command forces an image to download, whether it is already present or not.

####publish an image
    <repository_name>/<name>:<tag>
1. repository_name can be registry DNS or the name of a registry in the Docker Hub
2. tag is optional, when missing it is considered to be the latest by default

###Volumes
When a container writes files, it writes them inside of the container. Which means that when the container dies all of that data is lost  
Using a volume, you map a directory inside the container to a persistent storage. Persistent storages are managed through drivers, and they depend on the actual Docker host.  
    
    docker run -v /your/dir:/var/lib/mysql -d mysql:5.7 
        
It will ensure that any data written to the /var/lib/mysql directory inside the container is actually written to the /your/dir directory on the host system.   

###Docker File
A Docker image is created using the docker build command and a Dockerfile file. The Dockerfile file contains instructions on how the image should be built.  
The Dockerfile file can have any name. Naming it Dockerfile makes it easier for others to understand its purpose when they see that file in your project.  
It also means we don’t need to state the file name when using the docker build command.    
    
A Dockerfile file always begins with a FROM instruction because every image is based on another base image.   
This is a powerful feature since it allows you to extend images that may already be complex.
    
    FROM debian:8
    
    CMD ["echo", "Hello world"]
    
In order to create an image from my Dockerfile file, I need to run the docker build command. 
To do this, I type the following command in my terminal in the folder where the Dockerfile file lives.

    docker build .
    docker build -t hello .

-t is an optional parameter which is used to tag the image. An image can be created without a name, it would have an auto-generated unique ID.
The docker build command will create an image and store it locally in the system, and it can then be used to run like any other image.

###Creating an Including Files
suppose I want to create an image that includes a web server that serves a page over HTTP. We could add all these instructions to our debain image,
but it's easier to base our work on images that are already configured and tested.
    
    FROM nginx:1.15
    
    COPY index.html /usr/share/nginx/html

We don't need to include a CMD instruction in this file as the nginx:1.15 already contains a CMD instruction to run the NGINX server.    
        
    docker build -t webserver .
    docker run --rm -it -p 8082:80 webserver
    
In reality, server container would be long running, so you would run it without -t -rm.
    
    -t/-it : allows you to stop the container using Ctrl-C from the cmd line
    -rm : ensures that the container is deleted once it has stopped
    
####rmi 
When we build images using build command, they are created in our local machines. We could remove the images from our local machine using image name or image ID

    docker rmi c067edac5ec1
    docker rmi webserver:latest 
    
###Tags
    Image Name:
    
    <repository_name>/<name>:<tag>
           
Tag name is optional, when missing it is considered to be latest by default  
repostiory_name can be a registry DNS or the name of a registry in the Docker Hub

    docker build -t hello .
    
In this command, we didn't include a tag, therefore the default latest tag was used. The actual name is hello:latest.
    
    docker tag webserver learnbook/webserver

In case you want to add a tag to an existing image, the above command will do the trick. 
If the existing image already has a tag, this new tag will also get added to it and the same image will show under two different names.

    
####Why would you TAG your Images?
1. Be able to roll back to a previous version of an image if you detect a problem with the latest image.
2. Run different versions in different env. 
3. Run different version at the same time.
4. Deploy different version to different users.

#####Base Images
Its quite tempting to base your images on the latest ones so that you're always running up-to-date software, especially since it is straight forward.  
All we need to do is to omit the tag altogether or mention the latest one.
    
    From nginx:latest

Don't. First of all, it doesn't mean that any running container will be based on the latest version of the nginx image.  
Docker is about having reproducible images, so that latest version is evaluated when you build your image, not when the container is run. 
This means that the version will not change unless you run the docker build command again.

Secondly, you're likely to run into trouble. What about the nginx image releasing a new version with breaking chagnes. 
If you build your image again, you're  likely to get a broken image.

###Parameters as Env Variables

#####Providing Value
In order to provide an environment variable's value at runtime, you simply use the -e name=value parameter on the docker run command.
    
    docker run --rm -e host=www.google.com     
    
#####Default Value
You may also want to define a default value for an environment variable, in case it isn't provided when a container is created.  
This may be done in the Dockerfile file, using the ENV instruction.

    Env name=Dockie
name varible if not provided, will have a default value of Dockie.

#####Sample
    
    ping.sh
            #!/bin/sh
            
            echo "Pinging $host..."
            ping -C 5 $host
    
    Dockerfile
        
        From debian:8
        
        ENV host=www.google.com
        
        COPY ping.sh .
        
        CMD ["sh", "ping.sh"]  
        
    docker build -t pinger .
    docker run --rm pinger
    
    docker run --rm -e host=www.google.com            

####Storage

    VOLUME /path/to/directory
    
The /path/to/directory is a path to a directory used inside the image. When a container is created using the docker run command,  
the -v switch can be used to map this directory to an actual volume on the host system.    

This is only an indication. By default, if the user doesn't map this volume to an external store for the container,   
the data will be stored inside the container.

####Networking
When your image hosts server software, it listens on one or several ports. You can make this explicit using an EXPOSE instruction:
    
    EXPOSE 80
Using this instruction, is purely for documentation purpose. It will not open a port to the outside world, when a container is created from that image.
Anyone who creates a container will need to explicitly bind that port to an actual port of the host machine using the -p switch of the docker run command.    

##Publishing your Image

#####Registries
A docker registry is basically an image store that offers the following functions:
1. Ability to store various images
2. Ability to store various tags for the same image
3. An HTTP API that allows pushing images from a machine that produces them, or pull images to a machine that runs containers from those images.
4. TLS-secured connection to the API in order to avoid man-in-the-middle attacks.

#####Docker Hub
Docker Hub is a Docker Registry offered by Docker Inc. It allows unlimited storage of public images, and paid plans to host your private images.  
For publishing images to Docker
    
    docker login
    docker push learnbook/webserver
The docker push command is smart enough to push only the bits that differ from the base nginx image we used, since it is already stored in the Docker Hub.







#Kubernetes
Kubernetes is an open-source platform used for maintaining and deploying a group of containers