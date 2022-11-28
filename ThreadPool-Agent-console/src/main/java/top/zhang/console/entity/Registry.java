package top.zhang.console.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

/**
 * @author 98549
 * @date 2022/10/19 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registry {
    private String ip;
    private int port;
    private String token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registry registry = (Registry) o;
        return port == registry.port && Objects.equals(ip, registry.ip) && Objects.equals(token, registry.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port, token);
    }
}
