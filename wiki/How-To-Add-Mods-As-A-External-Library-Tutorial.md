# How to Add Mods as an External Library in IntelliJ IDEA

This tutorial will guide new developers through the process of adding mods and libraries as an API to their project using IntelliJ IDEA. The screenshots and instructions reflect the latest IntelliJ IDEA version (using the new UI). This is particularly useful when developing addons for Mysterium Fateboxes.

## Method 1: Using `build.gradle` (Recommended & Persistent)
The best way to add a local mod JAR as a dependency is by defining it in your `build.gradle` file. This ensures your changes persist when Gradle syncs.

1. **Open your project** in IntelliJ IDEA.
   
   ![Project View](images/Screenshot%202026-07-13%20173218.png)

2. **Open your `build.gradle`** file and navigate to the `dependencies` block.

   ![build.gradle View](images/Screenshot%202026-07-13%20174747.png)

3. **Add the dependency** using `implementation files(...)`. It is good practice to define the path to your library folder and the library name as variables.

   ```gradle
   var path : String = "E:\\Workspace\\Minecraft\\MysteriumFateboxes\\NeoForge 26.2\\MysteriumFateboxes\\build\\libs\\"
   var lib : String = "mysteriumfateboxes-1.0.8-release.jar"
   var libPath = path + lib
   implementation files(libPath)
   ```
   
   ![Adding Dependency](images/Screenshot%202026-07-13%20174732.png)

4. **Sync Gradle Changes**. In the top right of your editor, click the Gradle Sync icon to apply the changes and ensure the IDE recognizes the new library.

   ![Sync Gradle](images/Screenshot%202026-07-13%20174756.png)

---

## Method 2: Manually via Project Structure (For Verification)
You can also manually add or verify dependencies through the Project Structure menu. Note that if you *only* do this without updating `build.gradle`, your changes might be lost the next time Gradle syncs.

1. Open **File > Project Structure** (or press `Ctrl+Alt+Shift+S`) and navigate to **Modules > Dependencies**. Click the **`+`** icon to add a new dependency.

   ![Project Structure](images/Screenshot%202026-07-13%20173700.png)

2. Select **"2 Library..."** (or "JARs or Directories..." if you haven't defined it as a library yet).

   ![Select Library Menu](images/Screenshot%202026-07-13%20173712.png)

3. **Choose the library** you wish to add (e.g., `mysteriumfateboxes-1.0.8-release`) and click **Add Selected**.

   ![Choose Library](images/Screenshot%202026-07-13%20173847.png)

4. Verify that the library now appears in your dependencies list under the **Compile** scope. You might see a warning about changes being lost after reimporting—this is normal and reminds you to also update `build.gradle`.

   ![Verify Library 1](images/Screenshot%202026-07-13%20173920.png)
   ![Verify Library 3](images/Screenshot%202026-07-13%20173949.png)
   ![Verify Library 2](images/Screenshot%202026-07-13%20173929.png)

5. Click **Apply** and **OK**.

5. **Verify the Library in External Libraries**. Finally, check your project's External Libraries tree to confirm the JAR has been successfully synced and populated by Gradle.

   ![External Libraries Sync Verification](images/Screenshot%202026-07-13%20175853.png)