package com.pyy.NIO;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 10:06
 * @Description: 监控文件系统
 */
public class ChangeWatcher {

    public static void main(String[] args) {
        watch("F:\\Users\\jifang");
    }

    public static void watch(String directory) {
        try {
            WatchService service = FileSystems.getDefault().newWatchService();
            Paths.get(directory).register(service,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                WatchKey key = service.take();
                for (WatchEvent event : key.pollEvents()) {
                    System.out.println(event.context() + " 文件发生了 " + event.kind() + " 事件!");
                }

                if (!key.reset()) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
