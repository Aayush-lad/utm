package org.firewall;

import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public class DeepPacketInspectionHandler extends ChannelInboundHandlerAdapter {
    private final LanguageDetector detector = LanguageDetector.getDefaultLanguageDetector();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            String packetData = new String(bytes, StandardCharsets.UTF_8);

            LanguageResult result = detector.detect(packetData);
            if ("en".equals(result.getLanguage())) {
                System.out.println("Potential phishing packet detected: " + packetData);
                ctx.close();
                return;
            }
        }

        ctx.fireChannelRead(msg);
    }
}
