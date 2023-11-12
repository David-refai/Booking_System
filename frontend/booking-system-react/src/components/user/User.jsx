import { Wrap, WrapItem } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { GetUser } from "../../api/user";
import { UserCard } from "./UserCard";

export const User = () => {
    const [user, setUser] = useState([]);
    useEffect(() => {
        GetUser().then((user) => {
            setUser(user.data);
        });
    }, []);
    console.log(user);
    return <div>
        <Wrap spacing="34px" justify="center">
            {user.map((user) => (
                <WrapItem key={user.id}>
                    <UserCard user={user} />
                </WrapItem>
            ))}
        </Wrap>
  </div>;
};
