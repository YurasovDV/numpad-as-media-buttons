# Global hotkeys for playback control
Small script which is intended to be run as Windows Task. <br/>
It sets up global hotkeys: <numpad 5> as "media play/pause button", <numpad 7> as "media previous track", <numpad 9> as "next track"<br/>
How to launch: <br/>
1) install libraries from maven:<br/>
   net.java.dev.jna:jna:5.6.0<br/>
   net.java.dev.jna:jna-platform:5.6.0
2) build solution in IDE(I used IDEA Community)
3) Create windows task with trigger = at logon of our user<br/>
   run program: your path to javaw, for example "C:\Program Files\openjdk\bin\javaw.exe"<br/>
   command args: can be copied from IDEA output(includes classpath to JNA libs). <br/>
   May look like "-Dfile.encoding=UTF-8 -classpath %repo%\out\production\numpadasmediabuttons;C:\Users\%USER%\.m2\repository\net\java\dev\jna\jna\5.6.0\jna-5.6.0.jar;C:\Users\%USER%\.m2\repository\net\java\dev\jna\jna-platform\5.6.0\jna-platform-5.6.0.jar com.numpadasmediabuttons.HotKeyListener"<br/>
   disable flag "stop task if runs more than"<br/>
   
Motivation: spotify are not going to implement global hotkeys and I don't like installing third-party software just for this pretty basic functionality. 
Was this feature in winamp in 2005? Yes
No error handling, no logging, no warranty, use at your own risk
