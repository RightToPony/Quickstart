# Our Code

###### 22683 DEVO RedHawks

### **Version #1**

![Code version #1](https://sites.google.com/u/0/sitesv-images-rt/APXyLQeScSDGIgKuc9zZJ9K7l3Ggen_OW8sYPXpKUA77CpZZ6gjD2BuwCv9TTY654HExmGF5Bbb6Ug83aZ6hNitSNZf1UFU-NhIyXL9VxMbQbtG-MoZz3rVXEB70la7LV8YGnTM9lICsE_gVz54EzDfzI_LilcWGbyy9iKMsOAOdFVw_nfnr9xHt-c96CPDWoOOTdAiV1EXPH8nSC9U8q2mEMT0ntThyxM3Dqo9jAow2lVw=w1280)

##### I started with a basic TeleOp opmode built in OnBotJava, the control hub's built-in IDE. It allowed us to do some pretty basic things in terms of code, like getting our robot drving and shooting, but it was too limited and very hard to do autonomous in.

### **Version #2**

![Code version #2](https://sites.google.com/u/0/sitesv-images-rt/APXyLQemQvovP6AV1JZobqENe5mkUEfUYXTPSZafZzt1mt-BtlakuiYJcir1yfS_ABQ_-zW9uvQS5P1jl-Bc0LsM_tGb3LvRvBK5cfeJBq3Xw0QmN4zjUzfxQJuNuSsYRsEoINKfFGQmBKewG0cUg1OgXTFA3XnVv3ZzAlnVPbYHTV5e8oijdwnSGGZFbN_NOBAze1syaOTHSntXyX2GMetdAlIbNainq3ldInLHzGj1=w1280)
##### I brought all of our code into Android Studio which allowed me to start using a tool called PedroPathing which let me do some math on the four-bar odometry pods we attached to our robot (see the *our robot* section for more info) to set up an autocorrective DriveMode that would return to its original position if pushed while not moving. This would allow for me to start working on basic autonomous routines.

### **Version #3**
![Code version #3](https://sites.google.com/u/0/sitesv-images-rt/APXyLQfcwXD1mKAAi9nmYjVd_jfCvjqkFUftJzpjwo5bnrHhshhv5td7s8ebsmL-jZ-OzmqgoSYxeIVTTAdrhVuIRPjPNfaThTDuFtYQ_LXKiTHxXgSC_Mx15JendAB-JnQZJzxL6O1g3BVgjToUQk40POeoWW4_Gke9bS7GQdww2NophE-7WaW10VaU8fgCNgj4cmlZU-WTJyo6KYINHWP2x6eD6ZzLWcBIrQFr5-J9hlY=w1280)
##### After setting up PedroPathing I was able to begin building advanced autonomous OpModes.