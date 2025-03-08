package org.firewall;



import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class FirewallChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(new PacketFilterHandler());
        ch.pipeline().addLast(new StatefulPacketHandler());
        ch.pipeline().addLast(new DeepPacketInspectionHandler());
    }
}

