# MCML 
This repo is slapped together and rushed but hopefully ( if necessary ) I'll bring improvements. 

## What does this do? 
This repo houses both the plugin ( data generator ) and model builder ( jupyter notebook ) that I plan to use to identify and ban hackers playing on public minecraft networks. Anti-Cheat in Minecraft is difficult to develop because the server only has access to the packets sent by the client. And all checks have to be predetermined, usually they'll have issues and hacked game clients will be able to exploit them, but what if Deep Learning could generate a "perfect" anti cheat? Only using the packets sent by the server. Theorhetically it could train on real time data and adapt to new exploits within minutes. 

## How did you go about this? 
I used an approach described in this article [Splunk and TensorFlow for Security](https://www.splunk.com/en_us/blog/security/deep-learning-with-splunk-and-tensorflow-for-security-catching-the-fraudster-in-neural-networks-with-behavioral-biometrics.html) where you turn player ( or user ) movements and actions into an image, something that Neural Nets excel at indentifying! I had to create the dataset from scratch, so there was not much tuning that I could do. It is what it is I guess :) 

The images are 96x96 in this example but any size can be used it just depends on the size of the game area. Minecraft makes this pretty easy to convert into an image because the game is very literally made out of blocks. So I just have to convert each block into a pixel. So, keep in mind the conversion of 1 block = 1 pixel. 

Here's an image:
![Example Image](https://drive.google.com/uc?export=view&id=1-tppd4XTO6SDpOiHz67hRJbkyVT8oYiv)

Then the process I used for converting actions into an image was
1 -> a line between the players last block and new block is drawn 
2 -> if the line was blue last time its green now ( and vise versa ) I hoped this would help the net judge distance
3 -> if the player attacked set that pixel to red

to see the exact image code dip into src/DrawTask.java

