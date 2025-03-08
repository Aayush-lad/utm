package org.firewall.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class PacketSniffer extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() > 0) { // Ensure there is data to read
            byte[] packetData = new byte[in.readableBytes()];
            in.readBytes(packetData);  // Read the bytes from ByteBuf into the array

            PacketAnalyzer.analyzePacket(packetData);

            out.add(packetData);
        }
    }
}
