# Introduction #

The passwords are now stored as hashed values in the xml files (i.e., you won't be able to find the password by looking at the XML.


# Details #

Sample XML file for mdeng1990/pswd:
```
<user>
<name>mdeng1990</name>
<firstname>Michael</firstname>
<lastname>Deng</lastname>
<password>34a810</password>
<picture>src/arcade/database/images/default.jpg</picture>
<credits>0</credits>
</user>
```

The user XML files are stored in src/arcade/database/user.

The value given in the password tags is the hashed value of the password used at registration.  There is no (efficient) way of retrieving the password from the hash.  Thus password hashes are one-way and cannot be recovered.

During login the provided user password is hashed and compared to the saved hash value.  If they match, that is evidence that the given and original passwords match (it is extremely unlikely that two different strings will produce the same hash).

This is a security measure in order to prevent compromising user information in case the database is leaked.  In this arcade the database is stored locally anyway, but hashed values are still a measure of protection.

This is also the reason that websites with logins will not send you your original password (they cannot recover the password from the hash) and will  instead reset it to another value.  This functionality could be implemented into the arcade as well.