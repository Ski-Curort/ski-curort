import {
  Card,
  CardBody,
  Image,
  Heading,
  Stack,
  CardFooter,
  Button,
} from "@chakra-ui/react";
import useAppContext from "../../hooks/useAppContext";

export const Profile = () => {
  const context = useAppContext();

  return (
    <>
      <Card>
        <CardBody>
          <Image
            src={context.currentUser?.imageUrl}
            alt={context.currentUser?.displayName}
            borderRadius="full"
          ></Image>
          <Stack mt="8" spacing="4">
            <Heading size="md">{context.currentUser?.displayName}</Heading>
          </Stack>
        </CardBody>
        <CardFooter>
          <Button variant="solid" colorScheme="blue">
            Show Details
          </Button>
        </CardFooter>
      </Card>
    </>
  );
};
