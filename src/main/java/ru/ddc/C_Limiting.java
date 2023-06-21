package ru.ddc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C_Limiting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userLimit = scanner.nextInt();
        int serviceLimit = scanner.nextInt();
        int duration = scanner.nextInt();
        scanner.nextLine();

        Map<Integer, Integer> numberOfRequestsByUserId = new HashMap<>();
        Map<Integer, List<Integer>> userIdsByTimePoint = new HashMap<>();
        final int[] serviceRequestCount = {1};
        int temporary = 1;
        String data;
        while (!"-1".equals(data = scanner.nextLine())) {
            String[] str = data.split(" ");
            int time = Integer.parseInt(str[0]);
            int userId = Integer.parseInt(str[1]);

            numberOfRequestsByUserId.merge(userId, 1, Integer::sum);

            userIdsByTimePoint.merge(time, new ArrayList<>(List.of(userId)),
                    (list1, list2) -> Stream
                            .concat(list1.stream(), list2.stream())
                            .collect(Collectors.toList())
            );

            for (int j = temporary; j < (time - duration); j++) {
                List<Integer> userIds = userIdsByTimePoint.get(j);
                if (userIds != null) {
                    userIds.forEach(userId1 -> {
                        Integer userRequestCount = numberOfRequestsByUserId.get(userId1);
                        if (userRequestCount > 0) {
                            numberOfRequestsByUserId.replace(userId1, userRequestCount - 1);
                            serviceRequestCount[0]--;
                        }
                    });
                }
                temporary = j;
            }


            if (numberOfRequestsByUserId.get(userId) > userLimit) {
                System.out.println("429");
                numberOfRequestsByUserId.compute(userId,
                        (key, value) -> value == null ? 0 : value - 1);
            } else {
                if (serviceRequestCount[0] > serviceLimit) {
                    System.out.println("503");
                    numberOfRequestsByUserId.compute(userId,
                            (key, value) -> value == null ? 0 : value - 1);
                } else {
                    System.out.println("200");
                    serviceRequestCount[0]++;
                }
            }

            System.out.flush();
        }
    }
}