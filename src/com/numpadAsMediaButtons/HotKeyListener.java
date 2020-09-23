package com.numpadAsMediaButtons;

import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class HotKeyListener {

    private static final int PM_REMOVE = 1;

    private static final int NUMPAD_PAUSE_BUTTON = 5;
    private static final int MEDIA_PAUSE_VIRTUAL_KEY = 0xB3;

    private static final int NUMPAD_PREVIOUS_TRACK_BUTTON = 7;
    private static final int MEDIA_PREVIOUS_TRACK_VIRTUAL_KEY = 0xB1;

    private static final int NUMPAD_NEXT_TRACK_BUTTON = 9;
    private static final int MEDIA_NEXT_TRACK_VIRTUAL_KEY = 0xB0;

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(NUMPAD_PAUSE_BUTTON, MEDIA_PAUSE_VIRTUAL_KEY);
        map.put(NUMPAD_PREVIOUS_TRACK_BUTTON, MEDIA_PREVIOUS_TRACK_VIRTUAL_KEY);
        map.put(NUMPAD_NEXT_TRACK_BUTTON, MEDIA_NEXT_TRACK_VIRTUAL_KEY);

        com.sun.jna.platform.win32.User32 inst = com.sun.jna.platform.win32.User32.INSTANCE;
        // second parameter can be arbitrary(1, 2, 3) but for convenience I use identifiers, that are equal to digits
        inst.RegisterHotKey(null, NUMPAD_NEXT_TRACK_BUTTON, 0, KeyEvent.VK_NUMPAD9);
        inst.RegisterHotKey(null, NUMPAD_PREVIOUS_TRACK_BUTTON, 0, KeyEvent.VK_NUMPAD7);
        inst.RegisterHotKey(null, NUMPAD_PAUSE_BUTTON, 0, KeyEvent.VK_NUMPAD5);

        WinUser.MSG msg = new WinUser.MSG();
        while (true) {
            while (inst.PeekMessage(msg, null, 0, 0, PM_REMOVE)) {
                if (msg.message == WinUser.WM_HOTKEY) {

                    WinUser.INPUT input = new WinUser.INPUT();

                    input.type = new WinUser.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
                    input.input.setType("ki");
                    input.input.ki.wScan = new WinUser.WORD(0);
                    input.input.ki.time = new WinUser.DWORD(0);
                    input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
                    input.input.ki.wVk = new WinDef.WORD(map.get(msg.wParam.intValue()));
                    input.input.ki.dwFlags = new WinDef.DWORD(0);
                    inst.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
                }
            }
            Thread.sleep(50);
        }

        // relying on the fact that once process ends, handlers are unbound. IS NOT TESTED
//        inst.UnregisterHotKey(null, numpadPauseId);
//        inst.UnregisterHotKey(null, 7);
//        inst.UnregisterHotKey(null, 9);
    }
}
