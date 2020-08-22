# MCML 
This repo is slapped together and rushed but hopefully ( if necessary ) I'll bring improvements. 

## What does this do? 
This repo houses both the plugin ( data generator ) and model builder ( jupyter notebook ) that I plan to use to identify and ban hackers playing on public minecraft networks. Anti-Cheat in Minecraft is difficult to develop because the server only has access to the packets sent by the client. And all checks have to be predetermined, usually they'll have issues and hacked game clients will be able to exploit them, but what if Deep Learning could generate a "perfect" anti cheat? Only using the packets sent by the server. Theorhetically it could train on real time data and adapt to new exploits withing minutes. 

## How did you go about this? 
I used an approach described in this paper [Splunk and TensorFlow for Security](https://www.splunk.com/en_us/blog/security/deep-learning-with-splunk-and-tensorflow-for-security-catching-the-fraudster-in-neural-networks-with-behavioral-biometrics.html) where you turn player ( or user ) movements and actions into an image, something that Neural Nets excel at! Nothing really groundbraking was done here, there wasnt much data so not much tuning that I could do. It is what it is I guess :) 

The images are 96x96 in this example but any size can be used it just depends on the size of the game area. Minecraft makes this pretty easy to convert into an image because the game is very literally made out of blocks. So I just have to convert each block into a pixel. So, keep in mind the conversion of 1 block = 1 pixel. 

Here's an image:
![Example Image](https://drive.google.com/uc?export=view&id=1-tppd4XTO6SDpOiHz67hRJbkyVT8oYiv)

Then the process I used for converting actions into an image was
1 -> a line between the players last block and new block is drawn 
2 -> if the line was blue last time its green now ( and vise versa ) I hoped this would help the net judge distance
3 -> if the player attacked set that pixel to red

to see the exact image code dip into src/DrawTask.java

## That's cool and all, but how do I get involved
If you can tune a better model submit a PR! I'll read over every issue and PR ( because I don't expect there to be many ). 

If you can't do that here's some other ideas:

#### Java
Also, I've never commited a public java project so if I did something taboo please tell me :)

I'm not good at using the bukkit API full disclosure, currently when the p2p ( player to picture ) command is run a new thread is opened and the timer is run in that thread as to not disturb the game server. The thread is never closed. `thread.join()` causes the server to freeze until the thread returns so if anyone knows how to join a thread async that would be great! 

Also general Optimization is welcome

#### Data
I need LOTS of data. I can only collect so much and there's so many people with the ability to help, I hope to make the p2p plugin automatic some day so that it becomes even easier to collect data, but if you have time you can contact me about adding it to your server. 

