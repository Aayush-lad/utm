package org.firewall;

import redis.clients.jedis.Jedis;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;

public class StatefulPacketHandler extends ChannelInboundHandlerAdapter {
    private static final Jedis jedis = new Jedis("localhost");

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = remoteAddress.getAddress().getHostAddress();

        if (jedis.exists(ip)) {
            System.out.println("Resuming session for: " + ip);
        } else {
            System.out.println("New connection: " + ip);
            jedis.setex(ip, 600, "active"); // Store for 10 minutes
        }

        ctx.fireChannelActive();
    }
}
