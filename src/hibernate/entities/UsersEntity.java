package hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "precast_beam_system")
public class UsersEntity
{
    private String username;
    private String password;
    private String md5Check;

    @Id
    @Column(name = "username")
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "md5check")
    public String getMd5Check()
    {
        return md5Check;
    }

    public void setMd5Check(String md5Check)
    {
        this.md5Check = md5Check;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(md5Check, that.md5Check);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(username, password, md5Check);
    }
}
