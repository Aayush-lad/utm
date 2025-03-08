package org.firewall;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class PacketFilterHandler extends ChannelInboundHandlerAdapter {
    private static final Set<String> blockedIPs = new HashSet<>();
    private static final Set<Integer> blockedPorts = new HashSet<>();

    static {
        blockedIPs.add("192.168.1.100");
        blockedIPs.add("10.0.0.200");

        blockedPorts.add(23);  // Telnet
        blockedPorts.add(445); // SMB
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress remoteAddress = (InetSocketAddress) ((SocketChannel) ctx.channel()).remoteAddress();
        String ip = remoteAddress.getAddress().getHostAddress();
        int port = remoteAddress.getPort();

        if (blockedIPs.contains(ip) || blockedPorts.contains(port)) {
            System.out.println("Blocked connection from: " + ip + " on port: " + port);
            ctx.close();
            return;
        }

        System.out.println("Allowed connection from: " + ip + " on port: " + port);
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Packet processed...");
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

